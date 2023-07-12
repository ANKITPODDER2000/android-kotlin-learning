package com.example.runningapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.runningapplication.repositories.EntryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(val entryRepository: EntryRepository) : ViewModel()