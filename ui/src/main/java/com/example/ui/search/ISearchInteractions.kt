package com.example.ui.search

interface ISearchInteractions {
    fun onSearchChange(newValue: String)
    fun onClickTryAgain()
    fun navigateToPostDetails(postId: String)

}