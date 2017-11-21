package io.plan8.backoffice.model.item

import io.plan8.backoffice.model.Listable

/**
 * Created by chokwanghwan on 2017. 11. 5..
 */
class TaskItem(var customerName: String, var customerPhoneNumber: String, var customerAddress: String, var reservationDate: String, var reservationTime: String, var reservationEndTime: String, var productionName: String, var customerRequest: String, var productionDescription: String, var status:String, var closeStatus:String) : Listable