# Sectioned-RecyclerView
Easy implementation of RecyclerView with headers and items

### Demo
![gif](SectionRecyclerView.gif)

### Usage
#####Step 1. Add the JitPack repository to your build file
######Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
#####Step 2. Add the dependency
```groovy
dependencies {
	        compile 'com.github.IntruderShanky:Sectioned-RecyclerView:1.0.0'
	}
```
### Implementation
This is very easy and simple steps.
#####Step 1. Add RecyclerView in layout file:
```xml
<android.support.v7.widget.RecyclerView
   android:id="@+id/recycler_view"
   android:layout_width="match_parent"
   android:layout_height="wrap_content" />
```
#####Step 2. Create another layout file for Section Header:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingTop="26dp"
    android:paddingLeft="16dp"
    android:paddingRight="16dp"
    android:orientation="vertical">

    <TextView
        android:id="@+id/section"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="16sp"
        android:textSize="16sp"
        android:textStyle="bold"/>
    <View
        android:layout_width="match_parent"
        android:layout_height="1dp"
        android:layout_marginTop="5dp"
        android:background="#88424242"/>
</LinearLayout>
```

#####Step 3. Create another layout file for Section Child:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="8dp"
    android:orientation="vertical">

    <TextView
        android:id="@+id/child"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="16sp"
        android:text="mc sjcnscdj "
        android:padding="8dp"
        android:fontFamily="sans-serif-condensed"/>

</LinearLayout>
```

#####Step 4. Create a class for Section Child and named it. Here "Childs.java"
```java
public class Childs {

    String name;

    public Childs(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

#####Step 5. Create a class for Section Header and implement it with SectionItem. Here "Sections.java"
```java
import com.intrusoft.sectionrecyclerview.SectionItem;
import java.util.List;

public class Sections implements SectionItem {

    //Here Childs is the SectionChild class you recently created.
    List<Childs> childsList;
    
    //This is whatever you want in your section header.
    String sectionText;
   
   public Sections(List<Childs> childsList, String sectionText) {
        this.childsList = childsList;
        this.sectionText = sectionText;
    }

    @Override
    public List<?> getChildItems() {
        return childsList;
    }

    public String getSectionText() {
        return sectionText;
    }
}
```

#####Step 6. Create a ViewHolder class to hold the view. Here "HolderView.java"
```java
public class HolderView extends SectionViewHolder {

    //Views in Section Header
    TextView sectionText;

    public HolderView(View fullSectionView, View sectionView, View childs) {
        super(fullSectionView, sectionView, childs);
        //Initialize the views
        sectionText = (TextView) sectionView.findViewById(R.id.section);
    }
}
```
#####Step 7. Create a Adater for RecyclerView. Here "AdapterSectionRecycler.java"
```java

//extends it to SectionRecyclerViewAdapter, HolderView is ViewHolder class you had recently created
public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<HolderView> {

    List<Sections> sections;
    Context context;

    public AdapterSectionRecycler(Context context, List<Sections> sections, RecyclerView recyclerView, int sectionResId, int childResId) {
        super(context, sections, recyclerView, sectionResId, childResId);
        this.sections = sections;
        this.context =context;
    }

    @Override
    public HolderView onCreateSectionViewHolder(View fullSectionView, View sectionView, View child) {
        if(child == null){
            Toast.makeText(context, "NULL CHILD", Toast.LENGTH_SHORT).show();
        }
        // return the instance of ViewHolder class
        return new HolderView(fullSectionView, sectionView, child);
    }

    @Override
    //Bind Section Header here
    public void onBindSectionView(HolderView sectionHolder, int position, SectionItem sectionItem) {
        sectionHolder.sectionText.setText(sections.get(position).sectionText);
    }

    @Override
    //Bind Section Item here
    public View onBindChildView(int position, View view, Object childItem) {
        Childs child = (Childs) childItem;
        TextView childText = (TextView) view.findViewById(R.id.child);
        childText.setText(child.getName());
        return view;
    }
}
```

#####Step 8. Here complete code of your Activity Class, Here "MainActivity.java"
```java
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //setLayout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Create a List of Child DataModel
        List<Childs> childsList = new ArrayList<>();
        childsList.add(new Childs("April"));
        childsList.add(new Childs("Austin"));
        childsList.add(new Childs("Alex"));
        childsList.add(new Childs("Aakash"));

        //Create a List of Section DataModel implements SectionItem
        List<Sections> sections = new ArrayList<>();
        sections.add(new Sections(childsList, "A"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Bill Gates"));
        childsList.add(new Childs("Bob Proctor"));
        childsList.add(new Childs("Bryan Tracy"));
        sections.add(new Sections(childsList, "B"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Intruder Shanky"));
        childsList.add(new Childs("Invincible Vinod"));
        sections.add(new Sections(childsList, "I"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Jim Carry"));
        sections.add(new Sections(childsList, "J"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Neil Patrick Harris"));
        sections.add(new Sections(childsList, "N"));

        childsList = new ArrayList<>();
        childsList.add(new Childs("Orange"));
        childsList.add(new Childs("Olive"));
        sections.add(new Sections(childsList, "O"));


        AdapterSectionRecycler adapterRecycler = new AdapterSectionRecycler(this, sections, recyclerView, R.layout.section_layout, R.layout.item_layout);
        recyclerView.setAdapter(adapterRecycler);
        adapterRecycler.setOnItemClickListener(new SectionRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int sectionPosition, int itemPosition) {
                Log.d(sectionPosition + "", itemPosition + "");
            }
        });

        adapterRecycler.setOnSectionClickListener(new SectionRecyclerViewAdapter.OnSectionClickListener() {
            @Override
            public void onSectionClick(int position) {
                Log.d("sectionPosition", position + "");
            }
        });

    }
}

```

Licence
--------

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
