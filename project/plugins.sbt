logLevel := Level.Warn

addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.9.9")
addSbtPlugin(("com.eed3si9n" % "sbt-buildinfo" % "0.11.0").cross(CrossVersion.for3Use2_13))
