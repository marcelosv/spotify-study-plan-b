package com.marcelsouzav.spotify.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MusicJson {

    private String uuid;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String uuidCustomer;

    private String path;
}
