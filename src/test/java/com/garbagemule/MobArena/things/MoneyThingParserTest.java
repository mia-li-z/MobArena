package com.garbagemule.MobArena.things;

import com.garbagemule.MobArena.MobArena;
import net.milkbowl.vault.economy.Economy;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MoneyThingParserTest {

    private MoneyThingParser subject;
    private MobArena plugin;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        plugin = mock(MobArena.class);
        Economy economy = mock(Economy.class);
        when(plugin.getEconomy()).thenReturn(economy);

        subject = new MoneyThingParser(plugin);
    }


    @Test
    public void shortPrefix() {
        MoneyThing result = subject.parse("$500");

        assertThat(result, not(nullValue()));
    }

    @Test
    public void longPrefix() {
        MoneyThing result = subject.parse("money:500");

        assertThat(result, not(nullValue()));
    }


    @Test
    public void numberFormatForNaughtyValues() {
        exception.expect(NumberFormatException.class);
        subject.parse("$cash");
    }

}
