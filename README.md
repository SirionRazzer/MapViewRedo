# Unmaintained, see original here
* original: https://github.com/onlylemi/MapView
* this repository contains upstreamed build config with current (2020) common Android Studio setup and Gradle config
* I am not an author of the original codebase

# To get a Git project into your build: 

## Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
## Step 2. Add the dependency (choose proper 'Tag', see below)
```
	dependencies {
		implementation 'com.github.SirionRazzer:MapViewRedo:Tag'
	}
```
You can use the short commit hash or 'master-SNAPSHOT' as the version, ie. 'com.github.SirionRazzer:MapViewRedo:master-SNAPSHOT'

# MapView

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-MapView-green.svg?style=true)](https://android-arsenal.com/details/1/3497)
[![jitpack](https://img.shields.io/badge/jitpack-v1.0-green.svg)](https://jitpack.io/#onlylemi/mapview)
[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000)](https://github.com/onlylemi/MapView/blob/master/LICENSE)

This a indoor map view named MapView for `Android`. It also offer some layers. If you are doing a indoor map application and try to do it.

## Layers

* MapLayer
    * rotate
    * scale
    * slide
* LocationLayer
    * Sensor
* BitmapLayer
* MarkLayer
* RouteLayer
    * ShortestPath By [FloydAlgorithm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)
    * BestPath By [GeneticAlgorithm](https://en.wikipedia.org/wiki/Genetic_algorithm)， and you also look [here](https://github.com/onlylemi/GeneticTSP).

## Demo

I offer every layer demo and you can look the [demo](https://github.com/onlylemi/MapView/tree/master/demo) floder. And the following is a screenshot of demo.

![](https://raw.githubusercontent.com/onlylemi/notes/master/images/android_mapview_1.gif)
![](https://raw.githubusercontent.com/onlylemi/notes/master/images/android_mapview_2.gif)
![](https://raw.githubusercontent.com/onlylemi/notes/master/images/android_mapview_3.gif)
