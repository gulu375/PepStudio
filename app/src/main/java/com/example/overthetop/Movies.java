package com.example.overthetop;

import android.provider.MediaStore;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Movies implements Serializable {
    String Cast, Director,  Distributer, Movie_Name, Poster_Horizontal, Poster_Vertical, Genre, Serial, Story, Duration, VideoLink, FileLocation;
    float IMDB;
    int Metacritic, Rotten_Tomatoes, Year;
    boolean toDownload = false;

    boolean toWatch = false;
    boolean isFavourite = false;

    @Override
    public String toString() {
        return "Movies{" +
                "Cast='" + Cast + '\'' +
                ", Director='" + Director + '\'' +
                ", Distributer='" + Distributer + '\'' +
                ", Movie_Name='" + Movie_Name + '\'' +
                ", Poster_Horizontal='" + Poster_Horizontal + '\'' +
                ", Poster_Vertical='" + Poster_Vertical + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Serial='" + Serial + '\'' +
                ", Story='" + Story + '\'' +
                ", Duration='" + Duration + '\'' +
                ", VideoLink='" + VideoLink + '\'' +
                ", FileLocation='" + FileLocation + '\'' +
                ", IMDB=" + IMDB +
                ", Metacritic=" + Metacritic +
                ", Rotten_Tomatoes=" + Rotten_Tomatoes +
                ", Year=" + Year +
                ", toDownload=" + toDownload +
                ", toWatch=" + toWatch +
                ", isFavourite=" + isFavourite +
                '}';
    }

    public String getFileLocation() {
        return FileLocation;
    }

    public void setFileLocation(String fileLocation) {
        FileLocation = fileLocation;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String serial) {
        Serial = serial;
    }

    public Movies() {
    }

    public String getCast() {
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getDistributer() {
        return Distributer;
    }

    public void setDistributer(String distributer) {
        Distributer = distributer;
    }

    public float getIMDB() {
        return IMDB;
    }

    public void setIMDB(float IMDB) {
        this.IMDB = IMDB;
    }

    public int getMetacritic() {
        return Metacritic;
    }

    public void setMetacritic(int metacritic) {
        Metacritic = metacritic;
    }

    public String getPoster_Horizontal() {
        return Poster_Horizontal;
    }

    public void setPoster_Horizontal(String poster_Horizontal) {
        Poster_Horizontal = poster_Horizontal;
    }

    public String getPoster_Vertical() {
        return Poster_Vertical;
    }

    public void setPoster_Vertical(String poster_Vertical) {
        Poster_Vertical = poster_Vertical;
    }

    public int getRotten_Tomatoes() {
        return Rotten_Tomatoes;
    }

    public void setRotten_Tomatoes(int rotten_Tomatoes) {
        Rotten_Tomatoes = rotten_Tomatoes;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getMovie_Name() {
        return Movie_Name;
    }

    public void setMovie_Name(String movie_Name) {
        Movie_Name = movie_Name;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getVideoLink() {
        return VideoLink;
    }

    public void setVideoLink(String videoLink) {
        VideoLink = videoLink;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public boolean CheckMatch(@NonNull String prefix){
        return Movie_Name.toLowerCase().contains(prefix.toLowerCase());
    }

}
