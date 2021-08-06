package com.keygenqt.kchat.common.di

import com.keygenqt.kchat.common.service.BookService
import org.koin.dsl.module

val moduleServicesDI = module {
    single { BookService() }
}