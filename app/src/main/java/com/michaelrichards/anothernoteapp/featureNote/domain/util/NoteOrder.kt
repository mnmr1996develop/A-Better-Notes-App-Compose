package com.michaelrichards.anothernoteapp.featureNote.domain.util

sealed class NoteOrder(val orderType: OrderType){
    class Title(orderType: OrderType): NoteOrder(orderType)
    class EditedDate(orderType: OrderType): NoteOrder(orderType)
    class CreatedDate(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)
}
