// Copyright (c) 1998-2025 by Richard A. Wilkes. All rights reserved.
//
// This Source Code Form is subject to the terms of the Mozilla Public
// License, version 2.0. If a copy of the MPL was not distributed with
// this file, You can obtain one at http://mozilla.org/MPL/2.0/.
//
// This Source Code Form is "Incompatible With Secondary Licenses", as
// defined by the Mozilla Public License, version 2.0.

package gurps_test

import (
	"testing"

	"github.com/richardwilkes/gcs/v5/model/gurps"
	"github.com/richardwilkes/toolbox/check"
)

func TestWeaponBlock(t *testing.T) {
	for i, s := range []string{
		"0",
		"-1",
		"10",
		"No",
	} {
		check.Equal(t, s, gurps.ParseWeaponBlock(s).String(), "test %d", i)
	}

	cases := []struct {
		input    string
		expected string
	}{
		{"", "No"},
		{"-", "No"},
		{"+0", "0"},
		{"+1", "1"},
	}
	for i, c := range cases {
		check.Equal(t, c.expected, gurps.ParseWeaponBlock(c.input).String(), "test %d", i)
	}
}
