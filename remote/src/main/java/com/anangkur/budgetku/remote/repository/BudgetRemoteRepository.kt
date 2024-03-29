package com.anangkur.budgetku.remote.repository

import com.anangkur.budgetku.data.BaseFirebaseListener
import com.anangkur.budgetku.data.model.budget.CategoryEntity
import com.anangkur.budgetku.data.model.budget.CategoryProjectEntity
import com.anangkur.budgetku.data.model.budget.ProjectEntity
import com.anangkur.budgetku.data.model.budget.SpendEntity
import com.anangkur.budgetku.data.repository.budget.BudgetRemote
import com.anangkur.budgetku.remote.mapper.budget.CategoryMapper
import com.anangkur.budgetku.remote.mapper.budget.CategoryProjectMapper
import com.anangkur.budgetku.remote.mapper.budget.ProjectMapper
import com.anangkur.budgetku.remote.mapper.budget.SpendMapper
import com.anangkur.budgetku.remote.model.budget.CategoryRemote
import com.anangkur.budgetku.remote.model.budget.ProjectRemote
import com.anangkur.budgetku.remote.model.budget.SpendRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BudgetRemoteRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): BudgetRemote {

    private val categoryProjectMapper = CategoryProjectMapper.getInstance()
    private val categoryMapper = CategoryMapper.getInstance()
    private val projectMapper = ProjectMapper.getInstance()
    private val spendMapper = SpendMapper.getInstance()

    companion object{

        const val COLLECTION_PROJECT = "project"
        const val COLLECTION_CATEGORY = "categories"
        const val COLLECTION_SPEND = "spend"
        const val DOCUMENT_GENERAL_CATEGORIES = "general_categories"
        const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

        const val ERROR_NOT_FOUND = "data not found"

        private var INSTANCE: BudgetRemoteRepository? = null
        fun getInstance() = INSTANCE
            ?: BudgetRemoteRepository(
                FirebaseAuth.getInstance(),
                FirebaseFirestore.getInstance()
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
        idProject: String?,
        title: String,
        startDate: String,
        endDate: String,
        category: List<CategoryProjectEntity>,
        listener: BaseFirebaseListener<Boolean>
    ) {
        try {
            listener.onLoading(true)
            val createdAt = idProject ?: getCreatedAt()
            firebaseFirestore.collection(COLLECTION_PROJECT)
                .document(getUid())
                .collection(COLLECTION_PROJECT)
                .document(createdAt)
                .set(ProjectRemote(
                    id = createdAt,
                    title = title,
                    endDate = endDate,
                    listCategory = category.map {
                        categoryProjectMapper.mapToRemote(it)
                    },
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

    override fun getProject(listener: BaseFirebaseListener<List<ProjectEntity>>) {
        try {
            listener.onLoading(true)
            val listProject = ArrayList<ProjectEntity>()
            firebaseFirestore.collection(COLLECTION_PROJECT)
                .document(getUid())
                .collection(COLLECTION_PROJECT)
                .get()
                .addOnSuccessListener {
                    for (result in it) {
                        try {
                            val projectRemote = result.toObject(ProjectRemote::class.java)
                            listProject.add(projectMapper.mapFromRemote(projectRemote))
                        } catch (e: Exception) {
                            e.printStackTrace()
                            listener.onFailed(e.message ?: "")
                        }
                    }
                    listener.onLoading(false)
                    listener.onSuccess(listProject)
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

    override fun createSpend(spendEntity: SpendEntity, listener: BaseFirebaseListener<Boolean>) {
        try {
            val createdAt = getCreatedAt()
            listener.onLoading(true)
            firebaseFirestore.collection(COLLECTION_SPEND)
                .document(getUid())
                .collection(COLLECTION_SPEND)
                .document(spendEntity.idProject)
                .collection(COLLECTION_SPEND)
                .document(createdAt)
                .set(spendMapper.mapToRemote(spendEntity.apply {
                    date = createdAt
                }))
                .addOnSuccessListener {
                    listener.onLoading(false)
                    listener.onSuccess(true)
                }
                .addOnFailureListener {
                    listener.onLoading(false)
                    listener.onFailed(it.message ?: "")
                }
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message ?: "")
        }
    }

    override fun getListSpend(
        idProject: String,
        idCategory: String?,
        listener: BaseFirebaseListener<List<SpendEntity>>
    ) {
        try {
            val listSpendEntity = ArrayList<SpendEntity>()
            firebaseFirestore.collection(COLLECTION_SPEND)
                .document(getUid())
                .collection(COLLECTION_SPEND)
                .document(idProject)
                .collection(COLLECTION_SPEND)
                .get()
                .addOnSuccessListener {
                    for (result in it) {
                        val spendRemote = result.toObject(SpendRemote::class.java)
                        if (idCategory != null) {
                            if (spendRemote.idCategory == idCategory) {
                                listSpendEntity.add(spendMapper.mapFromRemote(spendRemote))
                            }
                        } else {
                            listSpendEntity.add(spendMapper.mapFromRemote(spendRemote))
                        }
                    }
                    listener.onLoading(false)
                    listener.onSuccess(listSpendEntity)
                }
                .addOnFailureListener {
                    listener.onLoading(false)
                    listener.onFailed(it.message ?: "")
                }
        } catch (e: Exception) {
            e.printStackTrace()
            listener.onLoading(false)
            listener.onFailed(e.message ?: "")
        }
    }

    override fun getProjectDetail(
        idProject: String,
        listener: BaseFirebaseListener<ProjectEntity>
    ) {
        try {
            listener.onLoading(true)
            firebaseFirestore.collection(COLLECTION_PROJECT)
                .document(getUid())
                .collection(COLLECTION_PROJECT)
                .document(idProject)
                .get()
                .addOnSuccessListener {
                    val project = it.toObject(ProjectRemote::class.java)
                    if (project != null) {
                        listener.onLoading(false)
                        listener.onSuccess(projectMapper.mapFromRemote(project))
                    } else {
                        listener.onLoading(false)
                        listener.onFailed(ERROR_NOT_FOUND)
                    }
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

    override fun deleteProject(idProject: String, listener: BaseFirebaseListener<Boolean>) {
        try {
            listener.onLoading(true)
            firebaseFirestore.collection(COLLECTION_PROJECT)
                .document(getUid())
                .collection(COLLECTION_PROJECT)
                .document(idProject)
                .delete()
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

}