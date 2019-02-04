/*
 * Copyright (C) 2019  Lightbend
 *
 * This file is part of ModelServing-tutorial
 *
 * ModelServing-tutorial is free software: you can redistribute it and/or modify
 * it under the terms of the Apache License Version 2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.lightbend.modelserving.akka

import akka.Done
import akka.actor.typed.ActorRef
import com.lightbend.model.winerecord.WineRecord
import com.lightbend.modelserving.model.{DataToServe, ModelToServe, ModelToServeStats, ServingResult}

// Controller
trait ModelServerActor
case class ModelUpdate(reply: ActorRef[Done], model : ModelToServe) extends ModelServerActor with ModelServerManagerActor
case class ServeData(reply: ActorRef[Option[ServingResult[Double]]], record : DataToServe[WineRecord]) extends ModelServerActor with ModelServerManagerActor
case class GetState(reply: ActorRef[ModelToServeStats], dataType : String) extends ModelServerActor with ModelServerManagerActor

// Controller manager
trait ModelServerManagerActor
case class GetModels(reply: ActorRef[GetModelsResult]) extends ModelServerManagerActor

// Reply messages
case class GetModelsResult(models : Seq[String])

