package com.music.oracle.utils

import org.scalatest.FunSpec
import play.api.libs.json.{Format, Json}
import spray.httpx.PlayJsonSupport

/**
	* Created on 3/31/16.
	*/
case class PostObject(userId: Int, id: Int, title: String, body: String)

object TestJsonSupport extends PlayJsonSupport {
	implicit val postObjectFmt: Format[PostObject] = Json.format[PostObject]
}

class HTTPUtilsTest extends FunSpec with PlayJsonSupport {
	it("Should correctly get a plain response from an HTTP GET Request") {
		val req = HTTPUtils.GET("http://jsonplaceholder.typicode.com/posts/1", Map())
		assert(req.isSuccess)
	}

	it("Should correctly get an already parsed response object from an HTTP GET Request") {
		val req = HTTPUtils.GET("http://jsonplaceholder.typicode.com/posts/1", Map())
		assert(req.isSuccess)
		import TestJsonSupport._
		val p = HTTPUtils.GETObject[PostObject]("http://jsonplaceholder.typicode.com/posts/1", Map())
		assert(p.isSuccess)
		assert(p.get.id == 1)
		assert(p.get.userId == 1)
	}


}
