## What Is PepStudio And Behind Motivation
### Introduction And Objective
PepStudio is a online OTT platform for streaming movies all around the globe. It is made for enhance the reach of film makers to the audiences in soft real time. You can watch movies that are made to stick to your mind. Watch the movies at highest resolution possible. You just need to make an account to access the app and you are just click away from the movies. You can create your account using your google, facebook or apple account or you can create by email id.

We are cinephiles. We are deeply exploring the cinema culture for many years and we realized that only making excellent cinemas is not enough to make a cinema successful. As Sir Satyajit Ray said producers also needs to take care of getting reach of as many people possible. Cinema Screens and Halls are playing the role for many decades. Later Television become a better bridge between creators and consumers of visual contents. But now times is far a head of those mediums. Internet connected peoples far beyond of unimaginable possibilities. By through this we can distribute much faster content globally. With this idea we are presenting to you the PepStudio, an over the top online platform. While we are talking about reaching to masses in india, we had to make an app that is compatible to all kind of devices. So we made it very lite and very less resource consuming. The whole app is only 17 megabytes. It puts very less pressure to the hardware.
# Frontend Description
## Flow Of The Software
### User Interaction
User will firstly enter the app and will see a welcome page with a begin button then with a click, they will be redirected to the login page. If user is already signed in then a fragment of 3 tabs will open else they can sign in with one touch sign in of google, facebook and apple. In the fragment there will 3 tabs that is movie feed, library and profile. The movie feed will be the list of movies that are in the server and a search button to find with name. In the library fragment It will show three short list of movies that you are selected as to watch, favourite and downloaded for offline streaming. In the profile fragment, it will represent dp of user, name and email id with list 3 buttons i.e change account, settings, logout will works as the name suggests. By clicking on a movie it will redirected to a page of description of the movie(cast, year, story, director, poster, distributor, ratings, etc.

![Flowchart](https://github.com/gulu375/PepStudio/assets/83973203/32950956-3218-49fb-ae01-7917e233ad6b)
![slide3](https://github.com/gulu375/PepStudio/assets/83973203/ffc9f6ef-d22e-460d-b867-1dcbd2004a7d)
![slide4](https://github.com/gulu375/PepStudio/assets/83973203/82dbd67c-c7e3-48d8-89a6-478b31a06225)
![slide5](https://github.com/gulu375/PepStudio/assets/83973203/0fc5006c-83f1-4924-9d8e-03a1db659777)
![slide6](https://github.com/gulu375/PepStudio/assets/83973203/6d117874-fd6d-45f9-a74b-f0e1e52f73d7)
![slide7](https://github.com/gulu375/PepStudio/assets/83973203/180b832f-b93e-4a2f-8968-fe9e1ad47871)
![slide8](https://github.com/gulu375/PepStudio/assets/83973203/288f1dfd-52b7-44ef-96a2-bfa4f11ce4a3)
![slide9](https://github.com/gulu375/PepStudio/assets/83973203/76e315ff-2867-44a4-82ec-e9eb05955ee2)
![slide10](https://github.com/gulu375/PepStudio/assets/83973203/81cbf0c8-c163-41f0-9328-f320ccad7b33)
# Backend Description
## Java Classes

## Main Activity
First starts with main activity where we check that if the user is signed in or not. If the user is signed in already then with an explicit intent it will go to the fragment activity else it will arrive to the login activity.
Used Functions: onCreate(), onClickListener(), setContextView(), GoogleSignIn.getLastSignedInAccount(), startActivity().

## Login Activity
In the login activity there are four kinds of authenticator those are Google Sign In, Facebook Sign In, Apple Sign In and Sign In with Email & Password. In this we will authenticate the user with their chosen way or create a new account.
Used Functions: inRecord(), validation(), onClickListener(), onCreate(), setContextView(), FirebaseAuth function set, Facebook Login API function set, startActivity(). 

## Fragment Activity
After that in fragment activity, there are three tabs those are movie feed, library and profile. Those are navigated with bottom navigation bar. Whenever user clicks any tab in navigation menu then it will replace the fragment with new instance of the chosen fragment.  By default the first fragment is movie feed.
Used Functions: onItemClickListener(), replaceFragment(), SharedPreferences function set, Gson function set, FragmentManager Function set, Fragment Transition function set..

## Movie Feed
The movie feed fragment is a view group of recycler view of span 2. The recycler view is 2 grid view of card layout. The cards will be loaded with movie objects. The Movie class is a blueprint of each movies. To load the card layouts into the recyclerview, we overridden the recyclerview adapter. 
User Functions: Fragment initialization function set, onCreate(), onCreateView(), View Inflater function set, startActivity(), onClickListener().

## Library Activity
In library feed is a gateway to three activity. Those activity collect three list those are made up by user. This is named Movie Library. This is also a fragment. Whenever a user clicks on a activity it opens it with explicit intent.
Used Functions: Fragment initialization function set, onCreate(), onCreateView(), onClickListener(), Intent function set.

## Profile Activity
This is the last fragment among the three tabs. This is made of user’s profile picture, name email address. Below of that there are three buttons for the user i.e. change account, settings and logout. On click change account, it explicitly intent to login activity and on log out, it gets the current signed in user and sign it out. After Finish, it restart the app.  This is named Profile Fragment.
Used Functions: onClickListener(), Fragment initialization function set, onCreate(), onCreateView(), onClickListener(), Intent function set, GoogleSignInAccount function set.

## Movie List Adapter / Search List Adapter
This very crucial class in the software. It is a custom recyclerview adapter. By default in packages there is already a recyclerview adapter but for primitive data types. While we are using a customised data type(Movies.java), we had to override the adapter class and it’s functions. It binds every card with it respective movies instance. 
Used Functions: onCreateViewHolder(), onBindViewHolder(), onClickListener(), viewHolder.class, getItemcount().

## Video Downloader
It is a java class used to download and fetch from the database needful videos and photos.
Used Functions: Underdeveloped.

## Video Player Activity
This activity is responsible for playing a video from the server or secondary storage. It is linked with a VideoView layout. It plays the movie from path or link. The video player is integrated with a seekbar and durations by which user can jump on any timestamp. It also had 10 sec jumping switch and play pause switch.
Used Function: sethadler(), Handler function set, Runnable function set, timerstring(), initializeseekbar(), onClickListener(), onReadyListener().

# Database Description

![slide14](https://github.com/gulu375/PepStudio/assets/83973203/8073d570-d3ef-4d3d-a6c9-a95c41ad3b43)

When the app searches for data then first it finds the data as shared preferences in internal storage. If it don’t find anything then it fetches from the database and stores it in the internal storage as shared preferences. It is a app specific storage, means no one can access those things without the app and deleted after uninstallation. When it had to fetch data from cloud server, it sends a request to the server administrator. Then it checks if the ask is according to the rule. If no then it disposes the request else it checks the type of the requested data. If it is a media file then it sends the access token of the request to the cloud manager and it provides the media file. Else if it is movie object then it sends the attached key of the request to the database manager and it sends object of the key. At last the object or media file sent to the backend as data snapshot.

![slide15-01](https://github.com/gulu375/PepStudio/assets/83973203/4dc5c3f9-4f39-4ec8-b389-96f6dfdf4a51)

