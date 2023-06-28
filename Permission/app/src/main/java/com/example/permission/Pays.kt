package com.example.permission

class Pays {
    var nom: String? = null
    var capitale: String? = null
    var desc: String? = null
    var drapeau = 0

    constructor() {}
    constructor(nom: String?, capitale: String?, drapeau: Int) {
        this.nom = nom
        this.capitale = capitale
        this.drapeau = drapeau
    }

    override fun toString(): String {
        return "Pays{" +
                "nom='" + nom + '\'' +
                ", capitale='" + capitale + '\'' +
                ", drapeau=" + drapeau +
                '}'
    }
}