package Models

import com.google.gson.annotations.SerializedName
import java.nio.file.Path

/*
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Song (

	@Transient var  path: Path? = null,
	@SerializedName("_version") val _version: String,
	@SerializedName("_songName") val _songName: String,
	@SerializedName("_songSubName") val _songSubName: String,
	@SerializedName("_songAuthorName") val _songAuthorName: String,
	@SerializedName("_levelAuthorName") val _levelAuthorName: String,
	@SerializedName("_beatsPerMinute") val _beatsPerMinute: Double,
	@SerializedName("_songTimeOffset") val _songTimeOffset: Double,
	@SerializedName("_shuffle") val _shuffle: Double,
	@SerializedName("_shufflePeriod") val _shufflePeriod: Double,
	@SerializedName("_previewStartTime") val _previewStartTime: Double,
	@SerializedName("_previewDuration") val _previewDuration: Double,
	@SerializedName("_songFilename") val _songFilename: String,
	@SerializedName("_coverImageFilename") val _coverImageFilename: String,
	@SerializedName("_environmentName") val _environmentName: String,
	@SerializedName("Models._difficultyBeatmapSets") val _difficultyBeatmapSets: List<_difficultyBeatmapSets>
)