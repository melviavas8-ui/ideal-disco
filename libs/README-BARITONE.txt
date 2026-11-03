Daamky 2.0 - Baritone API dependency

Download the Baritone Fabric API JAR for Minecraft 1.21.4, version 1.13.1.
The expected file name is:
  baritone-api-fabric-1.13.1.jar

Put the downloaded JAR directly in this folder (libs/), next to
nether-pathfinder-1.4.1.jar.

Do not use baritone-standalone-fabric-1.13.1.jar here. The standalone
artifact does not expose the public API needed to compile BaritoneController.

The Gradle build does not download this file automatically. Before Java
compilation, it checks that the expected public API classes exist in the JAR.
