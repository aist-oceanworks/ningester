/*****************************************************************************
 * Copyright (c) 2018 Jet Propulsion Laboratory,
 * California Institute of Technology.  All rights reserved
 *****************************************************************************/
package gov.nasa.jpl.nexus.ningester.processors;

import org.apache.sdap.nexusproto.GridTile;
import org.apache.sdap.nexusproto.NexusTile;
import org.apache.sdap.nexusproto.TileData;
import org.apache.sdap.nexusproto.TileSummary;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddTimeFromGranuleNameTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testSuccessfulMatch() {
        String regex = "^.*(\\d{7})\\.";
        String dateFormat = "yyyyDDD";

        String granuleName = "A2012001.L3m_DAY_NSST_sst_4km.nc";
        Long expectedTime = 1325376000L; // 01/01/2012 00:00:00 in epoch time
        NexusTile nexusTile = NexusTile.newBuilder().setSummary(
                TileSummary.newBuilder()
                        .setGranule(granuleName)
                        .build()
        ).setTile(
                TileData.newBuilder()
                        .setGridTile(
                                GridTile.newBuilder(

                                ).build()
                        ).build()
        ).build();

        AddTimeFromGranuleName processor = new AddTimeFromGranuleName(regex, dateFormat);

        NexusTile result = processor.setTimeFromGranuleName(nexusTile);

        assertThat(result.getTile().getGridTile().getTime(), is(expectedTime));
        assertThat(result.getSummary().getStats().getMinTime(), is(expectedTime));
        assertThat(result.getSummary().getStats().getMaxTime(), is(expectedTime));
    }

    @Test
    public void testUnparseable() {
        String regex = "^.*(\\d{7})\\.";
        String dateFormat = "yyyyDDDss";

        String granuleName = "A2012001.L3m_DAY_NSST_sst_4km.nc";

        thrown.expect(RuntimeException.class);
        thrown.expectCause(isA(ParseException.class));

        NexusTile nexusTile = NexusTile.newBuilder().setSummary(
                TileSummary.newBuilder()
                        .setGranule(granuleName)
                        .build()
        ).setTile(
                TileData.newBuilder()
                        .setGridTile(
                                GridTile.newBuilder(

                                ).build()
                        ).build()
        ).build();

        AddTimeFromGranuleName processor = new AddTimeFromGranuleName(regex, dateFormat);

        NexusTile result = processor.setTimeFromGranuleName(nexusTile);

    }
}