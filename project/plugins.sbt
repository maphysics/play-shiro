resolvers ++= Seq(
  DefaultMavenRepository,
  Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Other Repository" at "http://repo.typesafe.com/typesafe/repo/"
)

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.0")
