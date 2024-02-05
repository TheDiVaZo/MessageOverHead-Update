package me.thedivazo.messageoverhead.core.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DefaultMessageImpl<E, O, S> implements Message<E, O, S> {
    protected List<E> text;
    protected O owner;

    protected Collection<S> seers;

    @Override
    public List<E> getText() {
        return Collections.unmodifiableList(text);
    }

    @Override
    public void setText(List<E> text) {
        this.text = new ArrayList<>(text);
    }

    @Override
    public O getOwner() {
        return owner;
    }

    @Override
    public void setOwner(O owner) {
        this.owner = owner;
    }

    @Override
    public Collection<S> getSeers() {
        return Collections.unmodifiableCollection(seers);
    }

    @Override
    public void setSeers(Collection<S> collection) {
        this.seers = List.copyOf(collection);
    }
}
