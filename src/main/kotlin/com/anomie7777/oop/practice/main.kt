package com.anomie7777.oop.practice

import com.anomie7777.oop.practice.entity.Person
import com.anomie7777.oop.practice.entity.PlaceSearcher
import com.anomie7777.oop.practice.entity.Reservation
import com.anomie7777.oop.practice.entity.Schedule
import com.anomie7777.oop.practice.entity.vaccine.Vaccine

fun main() {
    val person = Person()
    val inoculableVaccineList = Vaccine.getInoculableVaccineList(person)
    val reservableScheduleList = getReservableScheduleList(person, inoculableVaccineList, "")
    val schedule = reservableScheduleList.firstOrNull()
    val inoculableVaccine = inoculableVaccineList.first()
    schedule?.let {
        saveReservation(schedule, inoculableVaccine, person)
    }
}

private fun saveReservation(
    schedule: Schedule,
    inoculableVaccine: Vaccine,
    person: Person
) {
    val reservation = schedule?.let {
        Reservation(schedule = schedule, vaccine = inoculableVaccine)
    }
    person.saveReservation(reservation)
}

fun getReservableScheduleList(person: Person, inoculableVaccineList: List<Vaccine>, locationCode: String): List<Schedule> {
    val result = mutableListOf<Schedule>()
    if (person.isSatisfyInoculationCondition()) {
        for (inoculableVaccine in inoculableVaccineList) {
            result.addAll(PlaceSearcher().findReservableSchedule(locationCode = locationCode, vaccine = inoculableVaccine))
        }
    }
    return emptyList()
}