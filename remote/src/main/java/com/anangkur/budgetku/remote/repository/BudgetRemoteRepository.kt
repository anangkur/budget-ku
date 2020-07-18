package com.anangkur.budgetku.remote.repository

import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.data.repository.budget.BudgetRemote
import com.anangkur.budgetku.remote.mapper.budget.CategoryMapper
import com.anangkur.budgetku.remote.mapper.budget.CategoryProjectMapper
import com.anangkur.budgetku.remote.model.budget.CategoryRemote
import com.anangkur.budgetku.remote.model.budget.ProjectRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BudgetRemoteRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage
): BudgetRemote {

    private val categoryProjectMapper = CategoryProjectMapper.getInstance()
    private val categoryMapper = CategoryMapper.getInstance()

    companion object{

        const val COLLECTION_PROJECT = "project"
        const val COLLECTION_CATEGORY = "categories"
        const val DOCUMENT_GENERAL_CATEGORIES = "general_categories"
        const val DOCUMENT_GENERAL = "general"
        const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

        private var INSTANCE: BudgetRemoteRepository? = null
        fun getInstance() = INSTANCE
            ?: BudgetRemoteRepository(
                FirebaseAuth.getInstance(),
                FirebaseFirestore.getInstance(),
                FirebaseStorage.getInstance()
            )
    }

    private fun getCreatedAt(): String {
        val date = Calendar.getInstance().time
        return SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault()).format(date)
    }

    private fun getUid(): String {
        return firebaseAuth.currentUser?.uid ?: ""
    }

    override fun createProject(
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProjectEntity>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        try {
            listener.onLoading(true)

            firebaseFirestore.collection(COLLECTION_PROJECT)
                .document(getUid())
                .collection(COLLECTION_PROJECT)
                .document(getCreatedAt())
                .set(ProjectRemote(
                    title = title,
                    endDate = endDate,
                    listCategory = category.map { categoryProjectMapper.mapToRemote(it) },
                    startDate = startDate
                ))
                .addOnSuccessListener {
                    listener.onLoading(false)
                    listener.onSuccess(true)
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(it.message ?: "")
                }
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message ?: "")
        }
    }

    override fun getCategory(listener: BaseFirebaseListener<List<CategoryEntity>>) {
        try {
            listener.onLoading(true)
            val listCategoryRemote = ArrayList<CategoryEntity>()
            firebaseFirestore.collection(COLLECTION_CATEGORY)
                .document(DOCUMENT_GENERAL_CATEGORIES)
                .collection(COLLECTION_CATEGORY)
                .get()
                .addOnSuccessListener {
                    for (result in it) {
                        try {
                            val categoryRemote = result.toObject(CategoryRemote::class.java)
                            listCategoryRemote.add(categoryMapper.mapFromRemote(categoryRemote))
                        } catch (e: Exception) {
                            e.printStackTrace()
                            listener.onLoading(false)
                            listener.onFailed(e.message ?: "")
                        }
                    }
                    listener.onLoading(false)
                    listener.onSuccess(listCategoryRemote)
                }
                .addOnFailureListener {
                    it.printStackTrace()
                    listener.onLoading(false)
                    listener.onFailed(it.message ?: "")
                }
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message ?: "")
        }
    }

}