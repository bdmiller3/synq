/*
 Copyright 2014 Red Hat, Inc. and/or its affiliates.

 This file is part of synq.

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.redhat.synq.testing.doubles;

import com.redhat.synq.AbstractEvent;
import com.redhat.synq.TimeKeeper;
import com.redhat.synq.TimeoutException;

import java.time.Duration;

public class NeverOccurringEvent extends AbstractEvent<Void> {
    private TimeKeeper timeKeeper;

    public NeverOccurringEvent() {
        this(TimeKeeper.systemTimeKeeper());
    }

    public NeverOccurringEvent(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;

        describedAs("an event that will never occur.");
    }

    @Override
    public Void waitUpTo(Duration duration) {
        timeKeeper.sleepFor(duration);

        if (Thread.currentThread().isInterrupted()) {
            return null;
        }

        throw new TimeoutException(this, duration);
    }
}