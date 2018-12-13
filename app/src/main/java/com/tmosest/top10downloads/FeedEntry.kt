package com.tmosest.top10downloads

class FeedEntry {
    var name: String = ""
    var artist: String = ""
    var releaseDate: String = ""
    var summary: String = ""
    var imageUrl: String = ""

    override fun toString(): String {
        return """
            name:           $name
            artist:         $artist
            release date:   $releaseDate
            summary:        $summary
            image url:      $imageUrl
        """.trimIndent()
    }
}