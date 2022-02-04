package com.suryansh.mynotes

import android.icu.text.CaseMap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.sql.StatementEvent


@Entity(tableName = "notesTable")
class Note(@ColumnInfo(name = "title")val noteTitle: String
, @ColumnInfo(name = "description")val noteDescription: String
,@ColumnInfo(name = "timestamp")val timestamp: String){

    @PrimaryKey(autoGenerate = true)var id =0

}