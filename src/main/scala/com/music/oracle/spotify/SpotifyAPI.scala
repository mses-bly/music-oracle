package com.music.oracle.spotify

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Success, Try}

/**
	* Created on 3/29/16.
	*/

object SpotifyAPI extends LazyLogging {

	private val CLIENT_ID = sys.env.getOrElse("SPOTIFY_CLIENT_ID", "")
	private val REDIRECT_URI = sys.env.getOrElse("SPOTIFY_REDIRECT_URI", "https://github.com/mses-bly/music-oracle")


	/**
		* Constructs the URL for requesting a user TOKEN to access Spotify user private data.
		* https://developer.spotify.com/web-api/authorization-guide/#authorization_code_flow
		*
		* @param clientId
		* @param redirectURI
		* @param scope : Most common scope to avoid issues with reduceLeft
		* @return
		*/
	def buildTokenURI(clientId: String = CLIENT_ID,
	                  redirectURI: String = REDIRECT_URI,
	                  scope: Seq[String] = Seq("user-read-private")
	                 ): Try[String] = {
		if (clientId.isEmpty) {
			Failure(new Exception("Spotify Client ID must be provided."))
		} else {
			Try(
				s"https://accounts.spotify.com/authorize/?" +
					s"client_id=$clientId" +
					s"&response_type=code" +
					s"&redirect_uri=${redirectURI.replace("/", "%2F").replace(":", "%3A")}" +
					s"&scope=${scope.reduceLeft((s1, s2) => s"$s1%20$s2")}"
			)
		}
	}

	/**
		* Simple interaction to obtain user token
		* @return
		*/
	def getUserToken(): Try[String] = {
		buildTokenURI() match {
			case Success(u) => {
				println(s"Please use this URL to obtain token: $u")
				print("Please paste user token: ")
				Try(io.Source.stdin.getLines().next())
			}
			case Failure(ex) => Failure(ex)
		}
	}

	def getUserStarredSongs() = {

	}

	def main(args: Array[String]) {
		println(getUserToken().getOrElse("TEST"))
	}
}
