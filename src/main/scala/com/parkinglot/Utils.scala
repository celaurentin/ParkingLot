package com.parkinglot

import com.parkinglot.models.ParkingLotData

object Utils {

  def readFile(fileName: String): List[ParkingLotData] = {
    val bufferedSource = io.Source.fromFile(fileName)
    val records = bufferedSource.getLines.drop(1).map { row =>
      val cols = row.split(",").map(_.trim)
      ParkingLotData(
        siteId = cols(0),
        transaction_id = cols(1),
        user_id = cols(2),
        vehicle_id = cols(3),
        payment_status = cols(4),
        vehicle_status = cols(5),
        self_park = cols(6),
        valet = cols(7),
        entry_time = cols(8),
        exit_time = cols(9),
        price = cols(10).toDouble
      )
    }.collect({ case pld: ParkingLotData => pld }).toList
    bufferedSource.close
    records
  }

  def totalRevenue(records: List[ParkingLotData]): Double =
    records.foldLeft(0.0)((acumm, item) => acumm + item.price)

  def totalForegoneRevenue(records: List[ParkingLotData]): Double =
    totalRevenue(records.filter(_.payment_status.contains("Payment Foregone")))

  def foregoneRatio(records: List[ParkingLotData]): Double = {
    totalForegoneRevenue(records) / totalRevenue(records)
  }

  def foregoneRatioBySite(records: List[ParkingLotData]): List[(String, Double)] = {
    val sitesMap = records.groupBy(_.siteId)
    sitesMap.view.mapValues(foregoneRatio).toSeq.sortWith(_._2 > _._2).toList

  }

}
