# TwoWaySlider
Two way slider is Range Slider that will help you to create Range Selector on Android!

# Screenshot
<img src="https://github.com/intsab/TwoWaySlider/blob/master/SampleTwoWaySeekbar/slider.png">
<h1>USAGE</h1>

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```java
 allprojects {
 	repositories {
		...
		maven { url 'https://jitpack.io' }
		}
	}
```
	
Step 2. Add the dependency
  
```java
	dependencies {
		implementation 'com.github.intsab:TwoWaySlider:1.3'
	}
``` 
Add This to your Layout File

```XML
 <com.intandif.twowayseekbar.RangeSeekBar
        android:id="@+id/rangeSliderView"
        android:layout_width="match_parent"
        android:layout_marginLeft="30dp"
        android:layout_marginRight="30dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        app:thumbRadius="120dp"
        app:trackHeight="4dp"
        app:trackTintColor="@color/colorAppLight" />
``` 
And in Activity 
 ```java
 RangeSeekBar seekBar= (RangeSeekBar)findViewById(R.id.rangeSliderView);
        seekBar.setOnValueChangedListener(new RangeSeekBar.onValueChangedListener() {
            @Override
            public void onValueChanged(int minimumValue, int maximumValue) {
                Log.d("","");
            }

            @Override
            public void onFinishScrolling(int minimumValue, int maximumValue) {
                Log.d("","");
            }
        });
``` 

if you want your custom range pass Array list in 
  ```java
  seekBar.setRange(mValues);
  ```
 <h1>Developers</h1>
 M Intsab Haider (Mobile & Web Application Developer)</br>
