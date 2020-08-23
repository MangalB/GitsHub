package com.bman.gitshub.data

import androidx.paging.PagingSource
import com.bman.gitshub.api.GithubService
import com.bman.gitshub.model.Repo
import java.lang.Exception

class RepoPagingSource(private val query:String, private val service: GithubService) : PagingSource<Int, Repo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val response = service.searchRepos(query, page, GithubRepository.NETWORK_PAGE_SIZE)
            LoadResult.Page(
                    response.items,
                    page - 1,
                    page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}