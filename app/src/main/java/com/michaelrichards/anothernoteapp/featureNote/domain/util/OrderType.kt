package com.michaelrichards.anothernoteapp.featureNote.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
