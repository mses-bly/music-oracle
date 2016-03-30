package com.music.oracle

import org.scalatest.FunSpec

/**
	* Created on 3/29/16.
	*/
class SpotifyAPITest extends FunSpec {
	it("Should correctly build a Token obtention URL") {
		assert(SpotifyAPI.buildTokenURL(scope = Seq("")).isFailure)

		val tokenURL = SpotifyAPI.buildTokenURL(clientId = "TEST_ID", scope = Seq("user-read-private", "user-read-email"))
		assert(tokenURL.isSuccess)
		assert(tokenURL.get.equals("https://accounts.spotify.com/authorize/?client_id=TEST_ID&response_type=code&redirect_uri=https%3A%2F%2Fgithub.com%2Fmses-bly%2Fmusic-oracle&scope=user-read-private%20user-read-email"))
	}

}