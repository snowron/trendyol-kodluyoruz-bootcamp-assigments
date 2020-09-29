package com.kodluyoruz.playlistapi.Contracts.Requests;

import lombok.*;

@Getter
@Setter
@Builder
public class AddTrackRequestBody {
    @NonNull
    String name;
    @NonNull
    String length;
    @NonNull
    String artist;
}

