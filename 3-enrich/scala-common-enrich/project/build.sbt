javacOptions          ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
scalaVersion          :=  "2.10.6"
scalacOptions         :=  Seq("-deprecation", "-encoding", "utf8", "-unchecked", "-feature")
scalacOptions in Test :=  Seq("-Yrangepos")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}
