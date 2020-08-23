/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bman.gitshub.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.bman.gitshub.data.GithubRepository
import com.bman.gitshub.model.Repo
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel for the [SearchRepositoriesActivity] screen.
 * The ViewModel works with the [GithubRepository] to get the data.
 */
class SearchRepositoriesViewModel(private val repository: GithubRepository) : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    var currentRepos:Flow<PagingData<Repo>>? = null

    private var query:String? = null

    /**
     * Search a repository based on a query string.
     */
    suspend fun searchRepo(queryString: String): Flow<PagingData<Repo>> {
        return repository.getSearchResultStream(queryString)
    }

}