package com.example.networkmodule.model

import com.google.gson.annotations.SerializedName

data class CategoryData(
    @SerializedName("categories")
    val data: CategoryResult
)

data class CategoryResult(
    @SerializedName("category")
    val categories: List<Category>,
    @SerializedName("total")
    val total: Int
)

data class Category(
    @SerializedName("parent_categories")
    val parentCategories: List<CategoryParent>
)

data class CategoryParent(
    @SerializedName("title")
    val title: String
)