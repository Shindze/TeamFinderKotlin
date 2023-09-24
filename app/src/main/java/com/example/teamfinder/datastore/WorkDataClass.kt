package com.example.teamfinder.datastore

data class WorkDataClass(val workName: String, val workDescription: String, val workTag: String) {

}

val works = listOf(
    WorkDataClass(
        workName = "Разработчик",
        workDescription = "Требуется создатель моделей в 3д",
        workTag = "Game"
    ),
    WorkDataClass(
        workName = "Создатель",
        workDescription = "Мы ищем того, кто будет творить!",
        workTag = "Other"
    ),
    WorkDataClass(
        workName = "Игрок",
        workDescription = "Хочешь поиграть? Тебе к нам!",
        workTag = "Game"
    ),
    WorkDataClass(
        workName = "Водитель",
        workDescription = "Ищем водителей категории B",
        workTag = "Other"
    ),
    WorkDataClass(
        workName = "Разработчик",
        workDescription = "Требуется создатель моделей в 3д",
        workTag = "Game"
    ),
)