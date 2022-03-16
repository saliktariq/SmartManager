package app.smartmanager.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
/*
This code is from my own work that I carried out
during university project making 'Archelon App'.
 */
@Entity(tableName = "authentication")
data class Authentication(
    @PrimaryKey(autoGenerate = true)
    var uid: Long,
    var username: String,
    var pwd: Long,
    var email: String,
    var firstName: String,
    var authCode: Long  //Used to reset password
)