package com.openclassrooms.footplayers.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Model object representing a Player
 */


public class Player implements Parcelable {

        /** Identifier */
        private long id;

        /** Full name */
        private String name;

        /** Avatar */
        private String avatarUrl;

        /** Address */
        private String address;

        /** Phone number */
        private String phoneNumber;

        /** About me */
        private String aboutMe;

        private boolean isfavorite;

        /**
         * Constructor
         * @param id
         * @param name
         * @param avatarUrl
         */
        public Player(long id, String name, String avatarUrl, String address,
                      String phoneNumber, String aboutMe, boolean isFavorite) {
            this.id = id;
            this.name = name;
            this.avatarUrl = avatarUrl;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.aboutMe = aboutMe;
            this.isfavorite = isFavorite;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAboutMe() {
            return aboutMe;
        }

        public void setAboutMe(String aboutMe) {
            this.aboutMe = aboutMe;
        }

        public boolean isFavorite() {
            return isfavorite;
        }

        public void setIsFavorite(boolean isfavorite) {
            this.isfavorite = isfavorite;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player = ( Player ) o;
            return Objects.equals(id, player.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        public static final Creator<Player> CREATOR = new Creator<Player>() {
            @Override
            public Player createFromParcel(Parcel in) {
                return new Player (in);
            }
            @Override
            public Player[] newArray(int size) {
                return new Player[size];
            }
        };

        public Player(Parcel in) {
            this.id = in.readLong();
            this.name = in.readString();
            this.avatarUrl = in.readString();
            this.address = in.readString();
            this.phoneNumber = in.readString();
            this.aboutMe = in.readString();
            this.isfavorite = in.readInt() != 0;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.id);
            parcel.writeString(this.name);
            parcel.writeString(this.avatarUrl);
            parcel.writeString(this.address);
            parcel.writeString(this.phoneNumber);
            parcel.writeString(this.aboutMe);
            parcel.writeInt(isfavorite ? 1 : 0);
        }
    }
