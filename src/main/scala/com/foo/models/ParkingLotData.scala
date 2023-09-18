package com.foo.models

case class ParkingLotData(
  siteId: String,
  transaction_id: String,
  user_id: String,
  vehicle_id: String,
  payment_status: String,
  vehicle_status: String,
  self_park: String,
  valet: String,
  entry_time: String,
  exit_time: String,
  price: Double
)
