package com.first.firstapplication.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArtistCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String nick;
}