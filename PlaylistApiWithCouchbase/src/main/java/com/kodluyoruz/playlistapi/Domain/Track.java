package com.kodluyoruz.playlistapi.Domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Track {
    String name;
    String length;
    String artist;
}
