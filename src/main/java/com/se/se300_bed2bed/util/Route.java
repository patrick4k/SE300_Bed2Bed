package com.se.se300_bed2bed.util;

import java.io.Serializable;

public class Route implements Serializable {
    public LLAPosition startPosition;
    public LLAPosition endPosition;

    public Route(LLAPosition startPosition, LLAPosition endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
}
