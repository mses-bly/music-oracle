package com.music.oracle.utils

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import play.api.libs.json.{Format, Json}

import scala.util.{Failure, Success, Try}

/**
	* Created on 3/31/16.
	*/
object HTTPUtils {
	private val OK = 200

	def GET(url: String, headers: Map[String, String]): Try[String] = {
		try {
			val httpGet = new HttpGet(url)
			headers foreach { case (k, v) => httpGet.setHeader(k, v) }
			// execute the request
			val client = HttpClientBuilder.create().build()
			val response = client.execute(httpGet)
			if (response.getStatusLine.getStatusCode == OK) {
				val entity = response.getEntity
				val body = Success(scala.io.Source.fromInputStream(entity.getContent).mkString)
				EntityUtils.consume(response.getEntity)
				response.close()
				body
			}
			else {
				Failure(new Exception(response.getStatusLine.getReasonPhrase))
			}
		} catch {
			case e: Exception => Failure(e)
		}
	}

	def GETObject[T](url: String, headers: Map[String, String])(implicit fmt: Format[T]): Try[T] = {
		GET(url, headers) match {
			case Success(body) => Success(Json.fromJson(Json.parse(body)).get)
			case Failure(ex) => Failure(ex)
		}
	}
}
