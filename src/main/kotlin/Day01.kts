val(w,r)=args[0].toInt() to generateSequence(::readlnOrNull).map{it.toInt()}
println(r.windowed(w).map{it.sum()}.zipWithNext().map{(a,b)->b-a}.count{it>0})