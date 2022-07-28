package dev.rolandsarosy.newssample.common.extensions

import android.view.LayoutInflater
import android.view.ViewGroup

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T
