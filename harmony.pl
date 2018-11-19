
octave(X, Y) :- mod(X,12) =:= mod(Y,12).

perfect_fifth(X, Y) :- mod(X,12) =:= mod(Y + 7,12).
perfect_fifth(X, Y) :- mod(Y,12) =:= mod(X + 7,12).

% chord_mod/2 makes every integer I in a list equal to I % 12.
chord_mod([], []).
chord_mod([H|T], [R|T2]) :- 
	R is mod(H,12), % Using 'is' is important to ensure the arithmetic actually gets done.
	chord_mod(T, T2).
	
% chord_offsets adds O to every element of the list (or if O is unspecified, it uses -list[0])

chord_offsets([], [], _).
chord_offsets([C|T], [R|T2], O) :-
	R is C + O,
	chord_offsets(T, T2, O).
	
chord_offsets([], []).
chord_offsets([C|T], [R|T2]) :-  
	O is 0 - C,
	R is 0,
	chord_offsets(T, T2, O).
	

	% chord(quality, type, offsets)

chord(major,triad,[0,4,7]).
chord(minor,triad,[0,3,7]).
chord(diminished,triad,[0,3,6]).
chord(augmented,triad,[0,4,7]).
chord(major,seventh,[0,4,7,11]).
chord(major,seventh,[0,4,7,10]).%Dominant Seventh
chord(minor,seventh,[0,3,7,10]).
chord(diminished,seventh,[0,3,6, 10]). % Half-diminished
chord(diminished,seventh,[0,3,6, 9]). % Fully-diminished

% Which notes can you double?
%doubling(inversion, quality, type, voice that gets the doubled note (0=bass, 3=soprano), chord note that gets the double)

doubling(0,_,_,0,_).
doubling(1,_,_,3,_).
doubling(2,_,_,0,_).

doubling(1,minor,_,_,1). %Minor chords can get doubled 3rd
doubling(2,minor,_,_,1).
doubling(1,diminished,_,_,1). %Diminished chords can get doubled 3rd
doubling(N,_,_,_,_) :- N >= 3.

% Offsets

chord_matches_offsets(Chord, Offsets, Root, Inversion) :-
	Chord = [Bass|_],
	permutation(Chord, P),
		chord_offsets(P, O),
		chord_mod(O, M),
		list_to_set(M, S),
		S = Offsets,
		P = [Root|_],
		nth0(BassIndex, P, Bass),
		nth0(BassIndex, M, BassOffset),
		nth0(Inversion, S, BassOffset).

		
check_chord_progression([Chord, NextChord|Tail]) :-
	chord(Quality,Type,Offsets),
	chord_matches_offsets(Chord,Offsets,Root,Inversion),
	check_chord_progression([NextChord|Tail]).
	
check_chord_progression([Chord, Tail]) :-
	chord(Quality,Type,Offsets),
	chord_matches_offsets(Chord,Offsets,Root,Inversion).