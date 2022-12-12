package com.drodriguez.direhospitality

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
data class Usuario(
    var id: String,
    var nombre: String,
    var apellido: String,
    var dni: String,
    var fechaNacimiento: String,
    var correo: String,
    var password: String,
    var terminosAceptados: Boolean,
    var isAdmin: Boolean,
){


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Usuario

        if (id != other.id) return false

        return true
    }


}
