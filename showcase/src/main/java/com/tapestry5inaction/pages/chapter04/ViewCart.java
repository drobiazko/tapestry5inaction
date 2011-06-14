package com.tapestry5inaction.pages.chapter04;

import org.apache.tapestry5.annotations.DiscardAfter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

import com.tapestry5inaction.entities.ShoppingCart;
import com.tapestry5inaction.entities.Track;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

import java.util.List;

public class ViewCart {

    @SessionState
    @Property
    private ShoppingCart cart;

    private boolean cartExists;

    @Property
    private Track currentTrack;

    public Double getTotalPrice() {
        if (cartExists) {
            return cart.getSum();
        }
        return 0d;
    }


    public int getTrackCount() {
        if (cartExists) {
            return cart.getTracks().size();
        }

        return 0;
    }

    public boolean isCartEmpty() {
        return getTrackCount() != 0;
    }

    void onClear() {
        cart = null;
    }
}
