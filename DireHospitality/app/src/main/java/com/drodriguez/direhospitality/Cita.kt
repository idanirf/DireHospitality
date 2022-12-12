package com.drodriguez.direhospitality

data class Cita(
    var id: String,
    var medicinaGeneral: Boolean,
    var analisisClinicos: Boolean,
    var fechaCita: String,
    var horaCita: String,
    var minutoCita: String,
    var paciente: String
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cita

        if (id != other.id) return false

        return true
    }

}
