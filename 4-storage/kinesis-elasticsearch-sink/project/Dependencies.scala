/**
 * Copyright (c) 2014-2016 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

import sbt._

object Dependencies {

  val resolutionRepos = Seq(
    // For Snowplow and amazon-kinesis-connectors
    "Snowplow Analytics Maven releases repo" at "http://maven.snplow.com/releases/",
    "Snowplow Analytics Maven snapshot repo" at "http://maven.snplow.com/snapshots/",
    // For Scalazon
    "BintrayJCenter"                         at "http://jcenter.bintray.com",
    "Twitter maven repo"                     at "http://maven.twttr.com/",
    // For user-agent-utils
    "user-agent-utils repo"                  at "https://raw.github.com/HaraldWalker/user-agent-utils/mvn-repo/"
  )

  object V {
    // Java
    val logging              = "1.1.3"
    val slf4j                = "1.7.5"
    val kinesisClient        = "1.6.1"
    val kinesisConnector     = "1.1.2"

    object jest {
      val _1x                = "1.0.3"
      val _2x                = "2.0.3"
    }

    object elasticsearch {
      val _1x                = "1.7.5"
      val _2x                = "2.4.0"
    }

    // Scala
    val argot                = "1.0.1"
    val config               = "1.0.2"
    val scalaUtil            = "0.1.0"
    val snowplowCommonEnrich = "0.22.0"
    val igluClient           = "0.3.2"
    val scalazon             = "0.11"
    val scalaz7              = "7.0.0"
    val snowplowTracker      = "0.3.0"
    // Scala (test only)
    val specs2               = "2.2"
    val scalazSpecs2         = "0.1.2"
    // Scala (compile only)
    val commonsLang3         = "3.1"
  }

  object Libraries {
    // Java
    val logging              = "commons-logging"            %  "commons-logging"           % V.logging
    val slf4j                = "org.slf4j"                  %  "slf4j-simple"              % V.slf4j
    val log4jOverSlf4j       = "org.slf4j"                  %  "log4j-over-slf4j"          % V.slf4j
    val kinesisClient        = "com.amazonaws"              %  "amazon-kinesis-client"     % V.kinesisClient
    val kinesisConnector     = "com.amazonaws"              %  "amazon-kinesis-connectors" % V.kinesisConnector

    object jest {
      val _1x                = "io.searchbox"               %  "jest"                      % V.jest._1x
      val _2x                = "io.searchbox"               %  "jest"                      % V.jest._2x
    }

    object elasticsearch {
      val _1x                = "org.elasticsearch"          %  "elasticsearch"             % V.elasticsearch._1x
      val _2x                = "org.elasticsearch"          %  "elasticsearch"             % V.elasticsearch._2x
    }

    // Scala
    val argot                = "org.clapper"                %% "argot"                     % V.argot
    val config               = "com.typesafe"               %  "config"                    % V.config
    val scalaUtil            = "com.snowplowanalytics"      %  "scala-util"                % V.scalaUtil
    val scalazon             = "io.github.cloudify"         %% "scalazon"                  % V.scalazon
    val scalaz7              = "org.scalaz"                 %% "scalaz-core"               % V.scalaz7
    val snowplowTracker      = "com.snowplowanalytics"      %% "snowplow-scala-tracker"    % V.snowplowTracker
    // Intransitive to prevent the jar containing more than 2^16 files
    val snowplowCommonEnrich = "com.snowplowanalytics"      % "snowplow-common-enrich"     % V.snowplowCommonEnrich intransitive
    // Since Common Enrich is intransitive, we explicitly add Iglu Scala Client as a dependency
    val igluClient           = "com.snowplowanalytics"      %  "iglu-scala-client"         % V.igluClient
    // Scala (test only)
    val specs2               = "org.specs2"                 %% "specs2"                    % V.specs2         % "test"
    val scalazSpecs2         = "org.typelevel"              %% "scalaz-specs2"             % V.scalazSpecs2   % "test"
    // Scala (compile only)
    val commonsLang3         = "org.apache.commons"         % "commons-lang3"              % V.commonsLang3   % "compile"
  }

  def onVersion[A](all: Seq[A] = Seq(), on1x: => Seq[A] = Seq(), on2x: => Seq[A] = Seq()) = {
    if (BuildSettings.ElasticsearchVersion.equals("1x")) {
      all ++ on1x
    } else {
      all ++ on2x
    }
  }
}
