package com.michaelrichards.anothernoteapp.featureNote.domain.util

sealed class NoteOrder(val orderType: OrderType){
    class Title(orderType: OrderType): NoteOrder(orderType)
    class EditedDate(orderType: OrderType): NoteOrder(orderType)
    class CreatedDate(orderType: OrderType): NoteOrder(orderType)
    class Color(orderType: OrderType): NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this){
            is Title -> Title(orderType)
            is Color -> Color(orderType)
            is CreatedDate -> CreatedDate(orderType)
            is EditedDate -> EditedDate(orderType)
        }
    }
}
