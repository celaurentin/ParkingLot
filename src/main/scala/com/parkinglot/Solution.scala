package com.parkinglot

import com.parkinglot.Utils._

object Solution {

  def main(args: Array[String]): Unit = {
    val records = readFile("visit_data_iso8601.csv")
    val results = foregoneRatioBySite(records)
    results.sortBy(_._2)(Ordering[Double].reverse).foreach{ r =>
      println(s"Site Id: ${r._1}, Forgone ratio: ${BigDecimal(r._2).setScale(2, BigDecimal.RoundingMode.HALF_UP)}")
    }
  }

}
