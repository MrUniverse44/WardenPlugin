package me.blueslime.wardenplugin.colors;

import java.util.regex.Pattern;

public abstract class WardenColor<T, E> {

    protected final static Pattern GRADIENT_PATTERN = Pattern.compile("%\\(wardencolor start:([\\dA-Fa-f]{6})\\)(.*?)\\(end-point:([\\dA-Fa-f]{6})( (?:add:.+?){0,5})?\\)%");
    protected final static Pattern SOLID_PATTERN = Pattern.compile("%\\(wardencolor solid:([\\dA-Fa-f]{6})\\)(.*?)\\(end-point( (?:add:.+?){0,5})?\\)%");

    public abstract T build(E entry);
}
