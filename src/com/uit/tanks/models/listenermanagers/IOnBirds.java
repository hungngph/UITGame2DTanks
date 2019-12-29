package com.uit.tanks.models.listenermanagers;

import com.uit.tanks.models.Bird;

public interface IOnBirds {
    Bird getBird();

    void initNewBird(IOnExplosions iOnExplosions, IOnBirds iOnBirds);

    int getLifeBird();

    void remove(Bird bird);
}